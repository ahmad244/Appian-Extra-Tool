package com.my.plugins;

import org.apache.log4j.Logger;

import com.appiancorp.suiteapi.content.ContentService;
import com.appiancorp.suiteapi.expression.annotations.Function;
import com.appiancorp.suiteapi.expression.annotations.Parameter;
import com.appiancorp.suiteapi.knowledge.DocumentDataType;
import com.appiancorp.suiteapi.content.DocumentInputStream;
import com.appiancorp.suiteapi.expression.annotations.Category;
import org.apache.commons.io.IOUtils;
import java.util.Base64;

@Category("MyCustomFunctionsCategory")
public class DocumentToBase64 {

    private static final Logger LOG = Logger
        .getLogger(DocumentToBase64.class);

    // private final ContentService cs;

    // DocumentToBase64(ContentService contentService) {
    // this.cs = contentService;
    // }

    @Function
    public String documentToBase64(ContentService cs, @DocumentDataType @Parameter Long document) throws RuntimeException {
        // Debugging: Log the document to ensure it's set correctly
        try {
            LOG.info("Document ID received: " + document);

            // Validate if document is valid before proceeding
            if (document <= 0) {
                throw new RuntimeException("Invalid document value: " + document.toString());
            }

            DocumentInputStream documentStream = cs.getDocumentInputStream(document);
            if (documentStream == null) {
                throw new RuntimeException("Document doesn't exist");
            }
            byte[] documentBytes = IOUtils.toByteArray(documentStream);

            return Base64.getEncoder().encodeToString(documentBytes);

        } catch (Exception e) {
            LOG.error("Exception: " + e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

}
