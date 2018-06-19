using Business.Interfaces;
using EasyStreets.Validators;
using Domain.Dtos;
using Domain.Entities;
using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using EasyStreets.Senders.Email.Interfaces;
using EasyStreets.Senders.Email;
using Microsoft.Extensions.Configuration;
using EasyStreets.Views;
using Newtonsoft.Json;
using System.Collections.Generic;
using System.Linq;
using System.Net.Mail;

namespace EasyStreets.Controllers
{
    [AllowAnonymous]
    [Route("api/marker")]
    public class MarkerController : Controller
    {
        IMarkerServices markerServices;
        ITemplateEmailSender templateEmailSender;
        IConfiguration configuration;

        public MarkerController(IMarkerServices markerService, ITemplateEmailSender templateEmailSender, IConfiguration configuration)
        {
            this.markerServices = markerService;
            this.templateEmailSender = templateEmailSender;
            this.configuration = configuration;
        }


        [HttpGet]
        public IActionResult ListMarkers()
        {
            return Ok(this.markerServices.List());
        }

        [HttpGet]
        [Route("around")]
        public IActionResult GetMarkersAround([FromBody] MarkersAroundParametersDto param)
        {
            var validation = new MarkersAroundParametersValidation();
            var results = validation.Validate(param);
            if (results.IsValid)
            {
                return Ok(this.markerServices.GetMarkersArround(param));
            }
            else return BadRequest(results.Errors);
        }


        [HttpPost("geojson")]
        public IActionResult ExportGeoJson()
        {
            var list = this.markerServices.List();
            var email = "simas.lucas@hotmail.com";
            //var email = this.HttpContext.User.Identity.Name;
            var emailConfig = EmailConfiguration.GetFromConfiguration(configuration, "Lucas");

            var geojson = list.Select(x => new { Type = "Feature", Properties = new { Name = x.StreetFurniture.ToString() },
                Geometry = new { Type = "Point", Coordinates = new double[2] { x.Latitude, x.Longitude } } });


            //MailMessage mail = new MailMessage(emailConfig.Sender, "simas.lucas@hotmail.com", "GeoJson", JsonConvert.SerializeObject(geojson, Formatting.Indented));
            //SmtpClient client = new SmtpClient();
            //client.Port = emailConfig.SMTP.Port;
            //client.DeliveryMethod = SmtpDeliveryMethod.Network;
            //client.UseDefaultCredentials = false;
            //client.Host = emailConfig.SMTP.Server;
            //client.EnableSsl = true;
            //client.Send(mail);
            

            return Ok(geojson);
        }


        [HttpPost]
        public IActionResult AddMarker([FromBody] Marker marker)
        {
            var validation = new AddMarkerValidation();
            var results = validation.Validate(marker);
            if (results.IsValid)
            {
                var inserted = this.markerServices.AddMarker(marker);
                return Ok(inserted);
            }
            else
            {
                return BadRequest(results.Errors);
            }
        }

        [HttpPut]
        public IActionResult UpdateMarker([FromBody] Marker marker)
        {
            this.markerServices.Update(marker);
            return Ok();
        }

        [HttpDelete("{id}")]
        public IActionResult RemoveMarker(long id)
        {
            this.markerServices.Remove(id);
            return Ok();
        }
    }
}
