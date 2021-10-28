package model;

public enum Reactions {
    NON(0, "Like", "images/ic_like_post.png"),
    LIKE(1, "Like", "images/ic_like.png"),
    LOVE(2, "Love", "images/ic_tym.png"),
    HAHA(3, "Haha", "images/ic_haha.png"),
    SAD(4, "Sad", "images/ic_sad.png"),
    WOW(5, "Wow", "images/ic_wow.png"),
    ANGRY(6, "Angry", "images/ic_angry.png");

    private int id;
    private String name;
    private String imgSrc;

    Reactions(int id, String name, String imgSrc) {
        this.id = id;
        this.name = name;
        this.imgSrc = imgSrc;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getImgSrc() {
        return imgSrc;
    }
}
