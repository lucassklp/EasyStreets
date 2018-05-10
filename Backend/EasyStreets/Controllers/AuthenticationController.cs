using Security;
using EasyStreets.Security.JwtSecurity;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using Newtonsoft.Json;
using System.Security.Claims;
using Persistence;
using Domain.Entities;
using System;
using EasyStreets.Validators;

namespace EasyStreets.Controllers
{

    [Route("api/auth")]
    public class AuthenticationController : Controller
    {
        ISecurity<User> security;
        public AuthenticationController(ISecurity<User> security)
        {
            this.security = security;
        }

        [Route("login")]
        [HttpPost]
        [AllowAnonymous]
        public IActionResult Login([FromBody]User user)
        {
            if (user != null)
            {
                var validator = new LoginUserValidation();
                var results = validator.Validate(user);
                if (results.IsValid)
                {
                    return Ok(JsonConvert.SerializeObject(new
                    {
                        token = security.Login(user)
                    }));
                }
                else return BadRequest();
            }
            else return BadRequest();

        }

        [Route("logout")]
        [HttpPost]
        public IActionResult Logout()
        {
            throw new NotImplementedException();
        }

        //[Route("refresh-token")]
        //[HttpPost]
        //public IActionResult RefreshToken([FromBody]string token)
        //{
        //    if (string.IsNullOrWhiteSpace(token))
        //    {
        //        return BadRequest("refreshToken is not set.");
        //    }

        //    if (!JwtTokenStorage.TokenExists(token))
        //    {
        //        return Unauthorized();
        //    }

        //    throw new NotImplementedException();

        //    string newToken = "";
        //    JwtTokenStorage.RefreshToken(token, newToken);

        //    return Ok(JsonConvert.SerializeObject(new
        //    {
        //        newToken
        //    }));
        //}

    }
}
