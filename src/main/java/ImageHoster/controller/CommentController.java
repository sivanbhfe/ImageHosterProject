package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import ImageHoster.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    private ImageService imageService;

    @Autowired
    private CommentService commentService;

    //Adding comments
    @RequestMapping(value="/image/{id}/{title}/comments", method = RequestMethod.POST)
    public String addComments(@PathVariable("id") Integer id, @PathVariable("title") String title, Comment newComment, HttpSession session){
        User user = (User) session.getAttribute("loggeduser");
        newComment.setUser(user);
        Image image = imageService.getImageById(id);
        newComment.setImage(image);
        System.out.println("CREAM:"+newComment.getUser().getUsername());
        System.out.println("CREAM:"+newComment.getText());
        System.out.println("CREAM:"+newComment.getImage().getTitle());
        Date date = new Date();
        newComment.setCreatedDate(date);
        System.out.println("CREAM:"+newComment.getCreatedDate().toString());
        commentService.createComment(newComment);
        return "redirect:/images/"+id+"/"+title;
    }
}
