using Domain.Entities;
using Persistence;
using Repository.Interfaces;
using System;
using System.Collections.Generic;
using System.Text;

namespace Repository
{
    public class MarkerRepository: IMarkerRepository
    {
        DaoContext context;
        ICrud<long, Marker> crud;
        public MarkerRepository(DaoContext daoContext, ICrud<long, Marker> crud)
        {
            this.context = daoContext;
            this.crud = crud;
        }


        public Marker Insert(Marker m)
        {
            this.crud.Create(m);
            return m;
        }

    }
}
