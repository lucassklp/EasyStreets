﻿using System.Collections.Generic;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Authorization;
using Repository.Interfaces;
using Domain.Entities;

namespace DefaultArchitecture.Controllers
{
    [Route("api/values")]
    public class ValuesController : Controller
    {

        IUserRepository repository;

        public ValuesController(IUserRepository repository)
        {
            this.repository = repository;
        }

        // GET api/values
        [HttpGet]
        public IEnumerable<User> Get()
        {
            return repository.SelectAll();
        }

        // GET api/values/5
        [HttpGet("{id}")]
        public string Get(int id)
        {
            return "value";
        }

        // POST api/values
        [HttpPost]
        public void Post([FromBody]string value)
        {
        }

        // PUT api/values/5
        [HttpPut("{id}")]
        public void Put(int id, [FromBody]string value)
        {
        }

        // DELETE api/values/5
        [HttpDelete("{id}")]
        public void Delete(int id)
        {
        }
    }
}
