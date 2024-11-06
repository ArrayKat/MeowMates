using Supabase.Postgrest.Attributes;
using Supabase.Postgrest.Models;


namespace UnitTestProject.Models
{

    [Table("users")]
    public class Cats : BaseModel
    {
        [PrimaryKey("id")]
        public Guid Id { get; set; }

        [Column("name_cat")]
        public string? NameCat { get; set; }

        [Column("gender_id")]
        public int? IdGender { get; set; }

        [Column("age")]
        public int? Age { get; set; }

        [Column("breed_id")]
        public int? IdBreed { get; set; }

        [Column("weight")]
        public int? Weight { get; set; }

        [Column("description_cats")]
        public string? Description { get; set; }

        [Column("image_url")]
        public string? ImageUrl { get; set; }
    }
}
