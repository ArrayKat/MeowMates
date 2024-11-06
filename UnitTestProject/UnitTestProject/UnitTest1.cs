using Supabase;
using Supabase.Gotrue;
using Supabase.Postgrest.Responses;
using UnitTestProject.Models;

namespace UnitTestProject
{
    [TestClass]
    public class UnitTest1
    {
        #region [Supabase]
            static string _url = "https://jmyjaavkcodgurgtwrwn.supabase.co";
            static string _key = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpteWphYXZrY29kZ3VyZ3R3cnduIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Mjg1ODQ2OTIsImV4cCI6MjA0NDE2MDY5Mn0.y8cBwQ8JRcPMUb276IiIEDHxflWIAu-7ndEbs0lq3oI";
            static SupabaseOptions _options = new SupabaseOptions
            {
                AutoConnectRealtime = true
            };
            Supabase.Client supabase = new Supabase.Client(_url, _key, _options);

            public async void Initialize()
            {
                await supabase.InitializeAsync();
            }

            public UnitTest1()
            {
                Initialize();
            }
        #endregion

        public async Task<ModeledResponse<Users>> GetUsers() => await supabase.From<Users>().Get();
        public async Task<Session?>? CreateUser(string email, string password) => await supabase.Auth.SignUp(email, password);
        public async Task<ModeledResponse<Cats>> GetCats() => await supabase.From<Cats>().Get();
        public async Task<ModeledResponse<Chats>> GetChat() => await supabase.From<Chats>().Get();
        public async Task<ModeledResponse<Messages>> GetMessage() => await supabase.From<Messages>().Get();
        public async Task<ModeledResponse<Breeds>> GetBreed() => await supabase.From<Breeds>().Get();

        [TestMethod]
        public void GetUsersList()
        {
            var result = GetUsers();
            result.Wait();
            var users = result.Result.Models;
            Assert.IsNotNull(users);

        }

        [TestMethod]
        public void CreateUser_EmailPassword()
        {
            string email = "kolinichenko.05@mail.ru";
            string password = "123456";
            var session = CreateUser(email, password);
            session.Wait();
            var user = session.Result.User;
            Assert.IsNotNull(user);
        }

        [TestMethod]
        public void GetCat()
        {
            var result = GetCats();
            result.Wait();
            var cats = result.Result.Models;
            Assert.IsNotNull(cats);
        }

        [TestMethod]
        public void GetChats()
        {
            var result = GetChat();
            result.Wait();
            var chats = result.Result.Models;
            Assert.IsNotNull(chats);
        }

        [TestMethod]
        public void GetMessageUser()
        {
            var result = GetMessage();
            result.Wait();
            var message = result.Result.Models;
            Assert.IsNotNull(message);
        }
        [TestMethod]
        public void GetBreedCats()
        {
            var result = GetBreed();
            result.Wait();
            var breed = result.Result.Models;
            Assert.IsNotNull(breed);
        }
    }
}