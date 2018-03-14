using Domain.Entities;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Metadata.Builders;
using System;
using System.Collections.Generic;
using System.Text;

namespace Persistence.Map
{
    class MarkerMap : IEntityTypeConfiguration<Marker>
    {
        public void Configure(EntityTypeBuilder<Marker> builder)
        {
            builder.ToTable("marker");

            builder.Property(x => x.Latitude).HasColumnName("latitude");
            builder.Property(x => x.Longitude).HasColumnName("longitude");
            builder.Property(x => x.Title).HasColumnName("title").HasMaxLength(45);
            builder.Property(x => x.Description).HasColumnName("description").HasMaxLength(300);
            builder.Property(x => x.ID).HasColumnName("MarkerID");
            builder.Property(x => x.StreetFurniture).HasColumnName("street_forniture");

            builder.HasKey(x => x.ID);
        }
    }
}
