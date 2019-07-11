package ImageHoster.repository;

import ImageHoster.model.Comment;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;

@Repository
public class CommentRepository {

    @PersistenceUnit(name="imageHoster")
    private EntityManagerFactory emf;

    public Comment createComment(Comment comment){
        EntityManager em = emf.createEntityManager();
        EntityTransaction transaction = em.getTransaction();
        try{
            transaction.begin();
            em.persist(comment);
            transaction.commit();
        } catch (Exception e){
            transaction.rollback();
        }

        return comment;
    }

    public List<Comment> getAllComments() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Comment> query = em.createQuery("SELECT c from Comment c", Comment.class);
        List<Comment> resultList = query.getResultList();
        return resultList;
    }


}
