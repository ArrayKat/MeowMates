using Supabase.Postgrest.Attributes;
using Supabase.Postgrest.Models;

namespace UnitTestProject.Models
{
    [Table("users")]
    public class Users : BaseModel
    {
        [PrimaryKey("id")]
        public Guid Id { get; set; }

        [Column("name")]
        public string? Name { get; set; }
        [Column("surname")]
        public string? Surname { get; set; }
        [Column("patronymic")]
        public string? Patronymic { get; set; }
        [Column("telephone")]
        public string? Telephone { get; set; }
       
        [Column("image_url")]
        public string? ImageUrl { get; set; }
    }
}
