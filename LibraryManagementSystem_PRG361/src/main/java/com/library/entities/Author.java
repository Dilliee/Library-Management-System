package com.library.entities;

public class Author {
    private int authorID;
    private String name;
    private String biography;

    // Constructors
    public Author() {}

    public Author(String name, String biography) {
        this.name = name;
        this.biography = biography;
    }

    // Getters and Setters
    public int getAuthorID() { return authorID; }
    public void setAuthorID(int authorID) { this.authorID = authorID; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getBiography() { return biography; }
    public void setBiography(String biography) { this.biography = biography; }

    @Override
    public String toString() {
        return "Author [authorID=" + authorID + ", name=" + name + ", biography=" + biography + "]";
    }
}
