/*
 * regain/Thumbnailer - A file search engine providing plenty of formats (Plugin)
 * Copyright (C) 2011  Come_IN Computerclubs (University of Siegen)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 * Contact: Come_IN-Team <come_in-team@listserv.uni-siegen.de>
 */

package pl.mazur.pawel.thumbnails;


import io.github.makbn.thumbnailer.config.AppSettings;
import io.github.makbn.thumbnailer.exception.ThumbnailerException;
import io.github.makbn.thumbnailer.util.ResizeImage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;


public class NativeImageThumbnailer extends AbstractThumbnailer {
    private static final Logger mLog = LogManager.getLogger("NativeImageThumbnailer");
    public NativeImageThumbnailer(AppSettings appSettings) {
        super(appSettings);
    }

    public void generateThumbnail(File input, File output) throws ThumbnailerException {
        ResizeImage resizer = new ResizeImage(thumbWidth, thumbHeight);

        try {
            resizer.setInputImage(input);
            resizer.writeOutput(output);
        } catch (IOException e) {
            mLog.error(e);
            throw new ThumbnailerException("File format could not be interpreted as image", e);
        }

    }

    @Override
    public String[] getAcceptedMIMETypes() {
        return ImageIO.getReaderMIMETypes();
    }
}