package br.com.wellintonvieira.soccernews.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class News implements Parcelable {
    @PrimaryKey()
    private final int id;
    private final String title;
    private final String body;
    private final String link;
    private final String image;
    private int favorite;

    public News(int id, String title, String body, String link, String image, int favorite) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.link = link;
        this.image = image;
        this.favorite = favorite;
    }

    protected News(Parcel parcel) {
        id = parcel.readInt();
        title = parcel.readString();
        body = parcel.readString();
        link = parcel.readString();
        image = parcel.readString();
        favorite = parcel.readInt();
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getLink() {
        return link;
    }

    public String getImage() {
        return image;
    }

    public int getFavorite() {
        return favorite;
    }

    public void setFavorite() {
        if (this.favorite == 0) {
            this.favorite = 1;
        } else {
            this.favorite = 0;
        }
    }

    public static final Creator<News> CREATOR = new Creator<News>() {
        @Override
        public News createFromParcel(Parcel in) {
            return new News(in);
        }

        @Override
        public News[] newArray(int size) {
            return new News[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(title);
        parcel.writeString(body);
        parcel.writeString(link);
        parcel.writeString(image);
        parcel.writeInt(favorite);
    }
}
