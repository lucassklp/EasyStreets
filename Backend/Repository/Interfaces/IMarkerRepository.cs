using Domain.Entities;
using System;
using System.Collections.Generic;
using System.Text;

namespace Repository.Interfaces
{
    public interface IMarkerRepository
    {
        Marker Insert(Marker m);
        List<Marker> SelectAll();
        void Delete(long id);
        Marker Update(Marker item);
    }
}
