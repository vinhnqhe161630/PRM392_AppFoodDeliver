namespace FoodDeliver_API.ViewModel.Comment
{
    namespace FoodDeliver_API.Models.DTOs
    {
        public class EditCommentDto
        {
            public Guid Id { get; set; } // Comment ID to identify which comment to edit
            public string Content { get; set; }
            public int Vote { get; set; }
        }
    }

}
