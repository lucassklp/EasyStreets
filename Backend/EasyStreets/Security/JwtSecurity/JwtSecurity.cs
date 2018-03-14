using Extensions;
using Repository;
using Microsoft.IdentityModel.Tokens;
using System;
using System.Collections.Generic;
using System.IdentityModel.Tokens.Jwt;
using System.Security.Claims;
using System.Security.Principal;
using Persistence;
using Security;
using Domain.Entities;
using Repository.Interfaces;

namespace EasyStreets.Security.JwtSecurity
{
    public class JwtSecurity : ISecurity<User>
    {
        private IUserRepository userRepository;
        public JwtSecurity(DaoContext daoContext, IUserRepository userRepository)
        {
            this.userRepository = userRepository; 
        }

        public string Login(User identity)
        {
            var existUser = userRepository.Login(identity.Email, identity.Password.ToSHA512());
            if (existUser != null)
            {
                var token = this.GenerateToken(existUser);
                JwtTokenStorage.AddToken(existUser.ID, token);
                return token;
            }
            else return null;
        }

        public string Logout(string token)
        {
            //JwtTokenStorage.InvalidateToken()
            return null;
        }

        private string GenerateToken(User user)
        {
            var handler = new JwtSecurityTokenHandler();

            var claims = new List<Claim>
            {
                // Unique Id for all Jwt tokes
                new Claim(JwtRegisteredClaimNames.Jti, Guid.NewGuid().ToString()),
                // Issuer
                new Claim(JwtRegisteredClaimNames.Iss, JwtTokenDefinitions.Issuer),
                // Issued at
                new Claim(JwtRegisteredClaimNames.Iat, DateTimeOffset.UtcNow.ToUnixTimeSeconds().ToString(), ClaimValueTypes.Integer64),
                // User data
                new Claim(ClaimTypes.NameIdentifier, user.ID.ToString()),
                new Claim(ClaimTypes.Name, user.Email),
                new Claim("DisplayName", user.Name),
                
                // to invalidate the cookie
                new Claim(ClaimTypes.SerialNumber, user.ToMD5(u => u.ID.ToString(), u => u.Name, u => u.Email, u => u.Password, u => u.UserRoles.ToMD5(ur => ur.RoleId.ToString(), ur => ur.UserId.ToString()))),
                // custom data
                new Claim(ClaimTypes.UserData, user.ID.ToString())
            };

            // add roles
            foreach (var userRole in user.UserRoles)
            {
                claims.Add(new Claim(ClaimTypes.Role, userRole.Role.Description));
            }

            ClaimsIdentity identity = new ClaimsIdentity(new GenericIdentity(ClaimTypes.NameIdentifier, user.ID.ToString()), claims);

            var securityToken = handler.CreateToken(new SecurityTokenDescriptor
            {
                Issuer = JwtTokenDefinitions.Issuer,
                Audience = JwtTokenDefinitions.Audience,
                SigningCredentials = JwtTokenDefinitions.SigningCredentials,
                Subject = identity,
                Expires = DateTime.Now.Add(JwtTokenDefinitions.TokenExpirationTime),
                NotBefore = DateTime.Now
            });

            return handler.WriteToken(securityToken);
        }
    }
}
