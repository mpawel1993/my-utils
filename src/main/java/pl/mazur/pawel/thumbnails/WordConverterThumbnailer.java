package pl.mazur.pawel.thumbnails;

import com.spire.doc.Document;
import com.spire.doc.documents.ImageType;
import io.github.makbn.thumbnailer.config.AppSettings;
import io.github.makbn.thumbnailer.exception.ThumbnailerException;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WordConverterThumbnailer extends AbstractThumbnailer {

    public WordConverterThumbnailer(AppSettings appSettings) {
        super(appSettings);
    }
    public void generateThumbnail(File input, File output) throws ThumbnailerException {

        //Create a Document object
        Document doc = new Document();

        //Load a Word document
        doc.loadFromFile(input.getAbsolutePath());

        //Convert the whole document into individual buffered images
        BufferedImage[] pages = doc.saveToImages(ImageType.Bitmap);

        Image image = pages[0].getScaledInstance(thumbWidth, thumbHeight, Image.SCALE_SMOOTH);

        //Re-write the image with a different color space
        BufferedImage newImg = new BufferedImage(thumbWidth, thumbHeight, BufferedImage.TYPE_INT_RGB);
        newImg.getGraphics().drawImage(image, 0, 0, null);

        try {
            ImageIO.write(newImg, FilenameUtils.getExtension(output.getName()), output);
        } catch (IOException e) {
            throw new ThumbnailerException(e);
        }
    }

    public void generateThumbnail(File input, File output, String mimeType) throws IOException, ThumbnailerException {
        generateThumbnail(input, output);
    }

    @Override
    public String[] getAcceptedMIMETypes() {
        return new String[]{
                "application/vnd.ms-word",
                "application/vnd.openxmlformats-officedocument.wordprocessingml",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document",
                "application/wordperfect",
        };
    }

}
