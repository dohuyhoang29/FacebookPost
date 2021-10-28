package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import model.Account;
import model.Post;
import model.PostAudience;

import java.net.URL;
import java.util.ResourceBundle;

public class PostController implements Initializable {
    @FXML
    private ImageView audience;

    @FXML
    private Label caption;

    @FXML
    private Label date;

    @FXML
    private ImageView imgAngry;

    @FXML
    private ImageView imgHaha;

    @FXML
    private ImageView imgLike;

    @FXML
    private ImageView imgLove;

    @FXML
    private ImageView imgPost;

    @FXML
    private ImageView imgProfile;

    @FXML
    private ImageView imgSad;

    @FXML
    private ImageView imgVerifile;

    @FXML
    private ImageView imgWow;

    @FXML
    private HBox likeContainer;

    @FXML
    private Label nbComments;

    @FXML
    private Label nbReactions;

    @FXML
    private Label nbShares;

    @FXML
    private HBox reactionsContainer;

    @FXML
    private Label username;

    public void setData (Post post) {
        Image img;
        img = new Image(getClass().getResourceAsStream(post.getAccount().getProfileImg()));
        imgProfile.setImage(img);
        username.setText(post.getAccount().getName());
        if (post.getAccount().isVerified()) {
            imgVerifile.setVisible(true);
        } else {
            imgVerifile.setVisible(false);
        }
        date.setText(post.getDate());
        if (post.getAudience() == PostAudience.PUBLIC) {
            img = new Image(getClass().getResourceAsStream(PostAudience.PUBLIC.getImgSrc()));
        } else {
            img = new Image(getClass().getResourceAsStream(PostAudience.FRIENDS.getImgSrc()));
        }
        audience.setImage(img);
        if (!post.getCaption().isEmpty()) {
            caption.setText(post.getCaption());
        }
        if (!post.getImage().isEmpty()) {
            img = new Image(getClass().getResourceAsStream(post.getImage()));
            imgPost.setImage(img);
        } else {
            imgPost.setVisible(false);
        }

        nbReactions.setText(String.valueOf(post.getTotalReactions()));
        nbComments.setText(String.valueOf(post.getNbComments()));
        nbShares.setText(String.valueOf(post.getNbShares()));
    }

    private Post getPost(){
        Post post = new Post();
        Account account = new Account();
        account.setName("Do Huy Hoang");
        account.setProfileImg("/images/dohuyhoang.jpg");
        account.setVerified(false);
        post.setAccount(account);
        post.setDate("October 28, 2021 at 02:44 PM");
        post.setAudience(PostAudience.PUBLIC);
        post.setCaption("Demo JavaFXCSS");
        post.setImage("/images/post.jpg");
        post.setTotalReactions(2002);
        post.setNbComments(299);
        post.setNbShares(232);

        return post;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setData(getPost());
    }
}
