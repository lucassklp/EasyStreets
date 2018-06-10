using EasyStreets.Security.JwtSecurity.Interfaces;
using Microsoft.AspNetCore.Authentication.JwtBearer;
using Microsoft.AspNetCore.Authorization;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Logging;
using Microsoft.IdentityModel.Tokens;
using System.Threading.Tasks;

namespace EasyStreets.Security.JwtSecurity
{
    public static class JwtConfigure
    {
        public static void ConfigureJwtAuthorization(this IServiceCollection services)
        {
            services.AddAuthorization(auth =>
            {
                auth.AddPolicy("Bearer", new AuthorizationPolicyBuilder()
                    .AddAuthenticationSchemes(JwtBearerDefaults.AuthenticationScheme‌​)
                    .RequireAuthenticatedUser().Build());
            });
        }

        public static void ConfigureJwtAuthentication(this IServiceCollection services)
        {
            services.AddAuthentication(options =>
            {
                options.DefaultScheme = JwtBearerDefaults.AuthenticationScheme;
            })
            .AddJwtBearer(options =>
            {
                options.SaveToken = true;

                options.TokenValidationParameters = new TokenValidationParameters()
                {
                    IssuerSigningKey = JwtTokenDefinitions.IssuerSigningKey,
                    ValidAudience = JwtTokenDefinitions.Audience,
                    ValidIssuer = JwtTokenDefinitions.Issuer,
                    ValidateIssuerSigningKey = JwtTokenDefinitions.ValidateIssuerSigningKey,
                    ValidateLifetime = JwtTokenDefinitions.ValidateLifetime,
                    ClockSkew = JwtTokenDefinitions.ClockSkew
                };
            });
        }
    }
}
