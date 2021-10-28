package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.scene.paint.Color;
import model.Account;
import model.Post;
import model.PostAudience;
import model.Reactions;

public class PostController implements Initializable {
    @FXML
    private ImageView imgProfide;

    @FXML
    private Label username;

    @FXML
    private ImageView imgVerified;

    @FXML
    private Label date;

    @FXML
    private ImageView audience;

    @FXML
    private Label caption;

    @FXML
    private ImageView imgPost;

    @FXML
    private Label nbReactions;

    @FXML
    private Label nbComments;

    @FXML
    private Label nbShares;

    @FXML
    private HBox reactionsContainer;

    @FXML
    private ImageView imgLike;

    @FXML
    private ImageView imgLove;

    @FXML
    private ImageView imgCare;

    @FXML
    private ImageView imgHaha;

    @FXML
    private ImageView imgWow;

    @FXML
    private ImageView imgSad;

    @FXML
    private ImageView imgAngry;

    @FXML
    private ImageView imgReaction;

    @FXML
    private Label reactionName;

    @FXML
    private HBox likeContainer;

    @FXML
    private HBox commentContainer;

    @FXML
    private HBox shareContainer;

    private long startTime = 0;
    private Reactions currentReaction;
    private Post post;

    @FXML
    public void onLikeContrainerPressed (MouseEvent mouseEvent) {
        startTime = System.currentTimeMillis();
    }

    @FXML
    public void onLikeContainerMouseReleased (MouseEvent mouseEvent) {
        if (System.currentTimeMillis() - startTime > 500) {
            reactionsContainer.setVisible(true);
        } else {
            if (reactionsContainer.isVisible()) {
                reactionsContainer.setVisible(false);
            }
            if (currentReaction == Reactions.NON) {
                setReaction(Reactions.LIKE);
            } else {
                setReaction(Reactions.NON);
            }
        }
    }

    @FXML
    public void onReactionImgPressed (MouseEvent mouseEvent) {
        switch (((ImageView) mouseEvent.getSource()).getId()) {
            case "imgLove":
                setReaction(Reactions.LOVE);
                break;
            case "imgCare":
                setReaction(Reactions.CARE);
                break;
            case "imgHaha":
                setReaction(Reactions.HAHA);
                break;
            case "imgSad":
                setReaction(Reactions.SAD);
                break;
            case "imgWow":
                setReaction(Reactions.WOW);
                break;
            case "imgAngry":
                setReaction(Reactions.ANGRY);
                break;
            default:
                setReaction(Reactions.LIKE);
                break;
        }
        reactionsContainer.setVisible(false);
    }

    @FXML
    public void setReaction (Reactions reaction) {
        Image img = new Image(getClass().getResourceAsStream(reaction.getIgmSrc()));
        imgReaction.setImage(img);
        reactionName.setText(reaction.getName());
        reactionName.setTextFill(Color.web(reaction.getColor()));
        if (currentReaction == Reactions.NON) {
            post.setTotalReactions(post.getTotalReactions() + 1);
        }
        currentReaction = reaction;
        if (currentReaction == Reactions.NON) {
            post.setTotalReactions(post.getTotalReactions() - 1);
        }
        nbReactions.setText(String.valueOf(post.getTotalReactions()));
    }

    public void setData (Post post) {
        this.post = post;
        Image img;
        img = new Image(getClass().getResourceAsStream(post.getAccount().getProfileImg()));
        imgProfide.setImage(img);
        username.setText(post.getAccount().getName());
        if (post.getAccount().isVerified()) {
           imgVerified.setVisible(true);
        } else {
            imgVerified.setVisible(false);
        }
        date.setText(post.getDate());
        if (post.getAudience() == PostAudience.PUBLIC) {
            img = new Image(getClass().getResourceAsStream(PostAudience.PUBLIC.getImgSrc()));
        } else {
            img = new Image(getClass().getResourceAsStream(PostAudience.FRIENDS.getImgSrc()));
        }
        audience.setImage(img);

        if (post.getCaption() != null && !post.getCaption().isEmpty()) {
            caption.setText(post.getCaption());
        }
        if (post.getImage() != null && !post.getImage().isEmpty()) {
            img = new Image(getClass().getResourceAsStream(post.getImage()));
            imgPost.setImage(img);
        } else {
            imgPost.setVisible(false);
            imgPost.setManaged(false);
        }

        nbReactions.setText(String.valueOf(post.getTotalReactions()));
        nbComments.setText(post.getNbComments() + " comments");
        nbShares.setText(post.getNbShares() + " shares");

        currentReaction = Reactions.NON;

    }

    private Post getPost () {
        Post post = new Post();
        Account account = new Account();
        account.setName("Do Huy Hoang");
        account.setProfileImg("/images/dohuyhoang.jpg");
        account.setVerified(true);
        post.setAccount(account);
        post.setDate("October 28, 2021 at 09:03 PM");
        post.setAudience(PostAudience.FRIENDS);
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
