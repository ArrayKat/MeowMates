using Supabase.Postgrest.Attributes;
using Supabase.Postgrest.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UnitTestProject.Models
{
    [Table("chats")]
    public class Chats : BaseModel
    {
        [PrimaryKey("id")]
        public Guid Id { get; set; }

        [Column("creation_date")]
        public DateTime? CreationDate { get; set; }
    }
}
