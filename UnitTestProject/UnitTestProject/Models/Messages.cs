using Supabase.Postgrest.Attributes;
using Supabase.Postgrest.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace UnitTestProject.Models
{

    [Table("messages")]
    public class Messages : BaseModel
    {
        [PrimaryKey("id")]
        public Guid Id { get; set; }

        [Column("text")]
        public string? Text { get; set; }

        [Column("send_date")]
        public DateTime? SendDate { get; set; }

        [Column("sender_id")]
        public Guid IdUser { get; set; }

        [Column("chat_id")]
        public int IdChat { get; set; }
    }
}
