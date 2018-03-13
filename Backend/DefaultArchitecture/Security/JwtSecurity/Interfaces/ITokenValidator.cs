using Microsoft.AspNetCore.Authentication.JwtBearer;
using System.Threading.Tasks;

namespace DefaultArchitecture.Security.JwtSecurity.Interfaces
{
    interface ITokenValidator
    {
        Task ValidateAsync(TokenValidatedContext context);
    }
}
