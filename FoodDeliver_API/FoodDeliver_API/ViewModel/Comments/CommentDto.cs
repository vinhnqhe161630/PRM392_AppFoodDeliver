namespace FoodDeliver_API.ViewModel.Comments
{
    namespace FoodDeliver_API.Models
    {
        public class CommentDto
        {
            public Guid Id { get; set; }
            public string Content { get; set; }
            public int Vote { get; set; }
            public Guid userId { get; set; }
            public string UserName { get; set; }
            public DateTime CommentDate { get; set; }
            public string UserAva { get; set; }

        }
    }

}
