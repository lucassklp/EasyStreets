using System.IdentityModel.Tokens.Jwt;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;
using EasyStreets.Security.JwtSecurity.Interfaces;
using Domain.Entities;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Extensions;
using Repository.Interfaces;

namespace EasyStreets.Security.JwtSecurity
{
    public class JwtTokenValidation : ITokenValidator
    {
        ICrud<long, User> userCrud;

        public JwtTokenValidation(ICrud<long, User> userCrud)
        {
            this.userCrud = userCrud;
        }

        public async Task ValidateAsync(TokenValidatedContext context)
        {
            var userPrincipal = context.Principal;

            var claimsIdentity = context.Principal.Identity as ClaimsIdentity;
            if (claimsIdentity?.Claims == null || !claimsIdentity.Claims.Any())
            {
                context.Fail("This is not our issued token. It has no claims.");
                return;
            }

            var serialNumberClaim = claimsIdentity.FindFirst(ClaimTypes.SerialNumber);
            if (serialNumberClaim == null)
            {
                context.Fail("This is not our issued token. It has no serial.");
                return;
            }

            var userIdString = claimsIdentity.FindFirst(ClaimTypes.UserData).Value;
            if (!long.TryParse(userIdString, out long userId))
            {
                context.Fail("This is not our issued token. It has no user-id.");
                return;
            }

            var user = userCrud.Read(userId);
            var serialNumberUser = user.ToMD5(u => u.ID.ToString(), u => u.Name, u => u.Email, u => u.Password, u => u.UserRoles.ToMD5(ur => ur.RoleId.ToString(), ur => ur.UserId.ToString()));
            if (user == null || serialNumberClaim.Value != serialNumberUser)
            {
                // user has changed his/her password/roles/stat/IsActive
                context.Fail("This token is expired. Please login again.");
            }

            var accessToken = context.SecurityToken as JwtSecurityToken;
            if (accessToken == null || string.IsNullOrWhiteSpace(accessToken.RawData) || 
                !JwtTokenStorage.IsLoggedIn(userId, accessToken.RawData))
            {
                context.Fail("This token is not in our database.");
                return;
            }
        }
    }
}
