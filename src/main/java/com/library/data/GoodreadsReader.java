package com.library.data;

import com.library.model.Book;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.net.URLConnection;

public class GoodreadsReader {
    private static boolean bBook, bTitle, bAuthor, bDescription, bImageUrl;
    public Book parseXML(URLConnection conn)
    {
        Book book = null;
        bBook = bTitle = bAuthor = bDescription = bImageUrl = false;

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        try {
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(conn.getInputStream());
            while (xmlEventReader.hasNext())
            {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement())
                {
                    StartElement startElement = xmlEvent.asStartElement();
                    if (startElement.getName().getLocalPart().equalsIgnoreCase("book") && !bBook)
                    {
                        book = new Book();
                        bBook = true;
                    }
                    else if(startElement.getName().getLocalPart().equalsIgnoreCase("title") && !bTitle)
                    {
                        xmlEvent = xmlEventReader.nextEvent();
                        book.setTitle(xmlEvent.asCharacters().getData());
                        bTitle = true;
                    }
                    else if(startElement.getName().getLocalPart().equalsIgnoreCase("author") && !bAuthor)
                    {
                        while (!bAuthor && xmlEventReader.hasNext())
                        {
                            xmlEvent = xmlEventReader.nextEvent();
                            if (xmlEvent.isStartElement())
                            {
                                startElement = xmlEvent.asStartElement();
                                if (startElement.getName().getLocalPart().equalsIgnoreCase("name"))
                                {
                                    xmlEvent = xmlEventReader.nextEvent();
                                    book.setAuthor(xmlEvent.asCharacters().getData());
                                    bAuthor = true;
                                }
                            }
                        }
                    }
                    else if(startElement.getName().getLocalPart().equalsIgnoreCase("description") && !bDescription)
                    {
                        xmlEvent = xmlEventReader.nextEvent();
                        book.setDescription(xmlEvent.asCharacters().getData());
                        bDescription = true;
                    }
                    else if(startElement.getName().getLocalPart().equalsIgnoreCase("image_url") && !bImageUrl)
                    {
                        xmlEvent = xmlEventReader.nextEvent();
                        book.setImageUrl(xmlEvent.asCharacters().getData());
                        bImageUrl = true;
                    }
                }

                if (xmlEvent.isEndElement())
                {
                    EndElement endElement = xmlEvent.asEndElement();
                    if(endElement.getName().getLocalPart().equalsIgnoreCase("authors"))
                    {
                        break;
                    }
                }

            }
        } catch (IOException | XMLStreamException | ClassCastException e)
        {
            e.printStackTrace();
        }

        return book;
    }
}
