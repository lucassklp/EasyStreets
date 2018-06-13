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

        public void Delete(long id)
        {
            this.crud.Delete(id);
        }

        public Marker Insert(Marker m)
        {
            this.crud.Create(m);
            return m;
        }

        public List<Marker> SelectAll()
        {
            return this.crud.SelectAll();
        }

        public Marker Update(Marker item)
        {
            return this.crud.Update(item);
        }
    }
}
