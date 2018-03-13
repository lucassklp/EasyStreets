using Microsoft.AspNetCore.Antiforgery;
using Microsoft.AspNetCore.Builder;
using Microsoft.AspNetCore.Http;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DefaultArchitecture.Security.JwtSecurity
{
    public class JwtAntiforgeryTokenMiddleware
    {
        private readonly RequestDelegate _next;
        private readonly IAntiforgery _antiforgery;

        public JwtAntiforgeryTokenMiddleware(RequestDelegate next, IAntiforgery antiforgery)
        {
            _next = next;
            _antiforgery = antiforgery;
        }

        public Task Invoke(HttpContext context)
        {
            var path = context.Request.Path.Value;
            if (path != null && !path.StartsWith("/api/", StringComparison.OrdinalIgnoreCase))
            {
                var tokens = _antiforgery.GetAndStoreTokens(context);
                context.Response.Cookies.Append(
                    key: "XSRF-TOKEN",
                    value: tokens.RequestToken,
                    options: new CookieOptions
                    {
                        HttpOnly = false // Now JavaScript is able to read the cookie
                    });
            }
            return _next(context);
        }
    }
}
