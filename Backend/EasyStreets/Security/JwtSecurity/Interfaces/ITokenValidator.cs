using Microsoft.AspNetCore.Authentication.JwtBearer;
using System.Threading.Tasks;

namespace EasyStreets.Security.JwtSecurity.Interfaces
{
    interface ITokenValidator
    {
        Task ValidateAsync(TokenValidatedContext context);
    }
}
