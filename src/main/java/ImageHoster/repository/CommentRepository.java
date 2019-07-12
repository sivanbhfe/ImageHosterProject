package ImageHoster.repository;

import ImageHoster.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class CommentRepository {

    @PersistenceUnit(name="imageHoster")
    private EntityManagerFactory emf;

    public Comment createComment(Comment newComment){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            em.merge(newComment);
            transaction.commit();
        } catch (Exception e){
            e.printStackTrace();
            transaction.rollback();
        }

        return newComment;
    }

    public List<Comment> getAllComments() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Comment> query = em.createQuery("SELECT c from Comment c", Comment.class);
        List<Comment> resultList = query.getResultList();
        return resultList;
    }


}
