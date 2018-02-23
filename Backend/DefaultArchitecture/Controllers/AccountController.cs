﻿using DefaultArchitecture.Senders.Email;
using DefaultArchitecture.Senders.Email.Interfaces;
using DefaultArchitecture.Services;
using DefaultArchitecture.Services.Exceptions;
using DefaultArchitecture.Services.Interfaces;
using DefaultArchitecture.Validators;
using DefaultArchitecture.Views;
using Domain;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace DefaultArchitecture.Controllers
{
    //[Authorize(Roles = "Administrador")]
    [AllowAnonymous]
    [Route("api/account")]
    public class AccountController : Controller
    {
        private IUserServices userServices;
        private ILogger logger;
        private IViewRenderService renderService;
        private IConfiguration configuration;

        public AccountController(IUserServices userServices, 
            ILogger<AccountController> logger, 
            IViewRenderService renderService, 
            IConfiguration configuration)
        {
            this.userServices = userServices;
            this.logger = logger;
            this.renderService = renderService;
            this.configuration = configuration;
        }

        [HttpPost]
        public async Task<IActionResult> Register([FromBody] User user)
        {
            //Validate the user
            var validation = new RegisterUserValidation();
            var results = validation.Validate(user);
            
            //If if valid, then register
            if (results.IsValid)
            {
                try
                {

                    var userRegistred = this.userServices.Register(user);

                    var pageModel = new AccountCreatedSuccessfullyModel();
                    pageModel.Name = user.Name;
                    pageModel.Email = user.Email;

                    var emailConfig = EmailConfiguration.GetFromConfiguration(configuration, "No Reply");
                    EmailSender emailSender = new EmailSender(emailConfig);
                    emailSender.To = user.Email;

                    var accountCreatedEmailSender = new TemplateEmailSender<AccountCreatedSuccessfullyModel>(this.renderService);
                    accountCreatedEmailSender.EmailSender = emailSender;
                    accountCreatedEmailSender.PageModel = pageModel;
                    accountCreatedEmailSender.SendAsynchronous();

                    return Ok(userRegistred);
                }
                catch(UserExistentException ex)
                {
                    return BadRequest(new Error(ex));
                }
                catch(Exception ex)
                {
                    logger.LogError(ex, ex.Message);
                    return StatusCode(StatusCodes.Status500InternalServerError);
                }
            }
            else return BadRequest(results.Errors);
        }
    }
}
