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

public abstract class AbstractThumbnailer {

    protected int thumbHeight;
    protected int thumbWidth;
    protected boolean closed = false;
    protected AbstractThumbnailer(AppSettings appSettings) {
        thumbHeight = appSettings.getThumbHeight();
        thumbWidth = appSettings.getThumbWidth();
    }
    public int getCurrentImageWidth() {
        return thumbWidth;
    }
    public int getCurrentImageHeight() {
        return thumbHeight;
    }
    public synchronized void close() throws IOException {
        // Do nothing for now - other Thumbnailer may need cleanup code here.
        closed = true;
    }
    public String[] getAcceptedMIMETypes() {
        return new String[]{};
    }

    public void generateThumbnail(File input, File output, String mimeType) throws IOException, ThumbnailerException {
        // Ignore MIME-Type-Hint
        generateThumbnail(input, output);
    }
}
