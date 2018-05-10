using Business.Exceptions;
using Business.Interfaces;
using EasyStreets.Senders.Email;
using EasyStreets.Senders.Email.Interfaces;
using EasyStreets.Validators;
using EasyStreets.Views;
using Domain.Entities;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.Logging;
using System;
using System.Collections.Generic;
using FluentValidation.Results;
using Extensions;
using System.Linq;

namespace EasyStreets.Controllers
{
    [AllowAnonymous]
    [Route("api/account")]
    public class AccountController : Controller
    {
        private IUserServices userServices;
        private ILogger logger;
        private ITemplateEmailSender templateEmailSender;
        private IConfiguration configuration;

        public AccountController(IUserServices userServices, 
            ILogger<AccountController> logger,
            ITemplateEmailSender templateEmailSender,
            IConfiguration configuration)
        {
            this.userServices = userServices;
            this.logger = logger;
            this.templateEmailSender = templateEmailSender;
            this.configuration = configuration;
        }

        [HttpPost]
        [Route("register")]
        [Produces("application/json")]
        public IActionResult Register([FromBody] User user)
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
                    return Ok(userRegistred);
                }
                catch(UserExistentException ex)
                {
                    logger.LogInformation(ex.Message);
                    return BadRequest(new { ex.Message });
                }
                catch(Exception ex)
                {
                    logger.LogError(ex, ex.Message);
                    return StatusCode(StatusCodes.Status500InternalServerError);
                }
            }
            else return BadRequest(results.Errors.Select(x => new { x.PropertyName, x.ErrorMessage }));
        }
    }
}
