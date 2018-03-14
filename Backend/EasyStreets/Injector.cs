using Business;
using Business.Interfaces;
using EasyStreets.Security.JwtSecurity;
using EasyStreets.Security.JwtSecurity.Interfaces;
using EasyStreets.Senders.Email;
using EasyStreets.Senders.Email.Interfaces;
using EasyStreets.Services;
using Domain.Entities;
using Microsoft.Extensions.DependencyInjection;
using Repository;
using Repository.Interfaces;
using Security;

namespace EasyStreets
{
    public static class Injector
    {
        /// <summary>
        /// This extension method inject the things you need into your application
        /// </summary>
        /// <param name="services"></param>
        public static void InjectServices(this IServiceCollection services)
        {
            // Injecting the service for TemplateEmailSender
            services.AddScoped<IViewRenderService, ViewRenderService>();
            services.AddScoped<ITemplateEmailSender, TemplateEmailSender>();

            // Injecting security resources
            services.AddScoped(typeof(ISecurity<User>), typeof(JwtSecurity));
            services.AddScoped<ITokenValidator, JwtTokenValidation>();

            // Injecting services
            services.AddTransient<IUserServices, UserServices>();
            services.AddTransient<IMarkerServices, MarkerServices>();

            // Injecting for database handles
            services.AddScoped(typeof(ICrud<,>), typeof(Crud<,>));
            services.AddTransient<IUserRepository, UserRepository>();
            services.AddTransient<IUserRepositoryAsync, UserRepository>();
            services.AddTransient<IMarkerRepository, MarkerRepository>();
        }
    }
}
