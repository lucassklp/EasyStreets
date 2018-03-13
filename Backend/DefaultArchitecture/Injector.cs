using Business;
using Business.Interfaces;
using DefaultArchitecture.Security.JwtSecurity;
using DefaultArchitecture.Security.JwtSecurity.Interfaces;
using DefaultArchitecture.Senders.Email;
using DefaultArchitecture.Senders.Email.Interfaces;
using DefaultArchitecture.Services;
using Domain.Entities;
using Microsoft.Extensions.DependencyInjection;
using Repository;
using Repository.Interfaces;
using Security;

namespace DefaultArchitecture
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
