using Supabase.Postgrest.Attributes;
using Supabase.Postgrest.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UnitTestProject.Models
{
    

    [Table("breeds")]
    public class Breeds : BaseModel
    {
        [PrimaryKey("id")]
        public Guid Id { get; set; }

        [Column("breed")]
        public string? Breed { get; set; }
    }
}
