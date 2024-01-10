package com.my.plugins;

import org.apache.log4j.Logger;

import com.appiancorp.suiteapi.common.Name;
import com.appiancorp.suiteapi.content.ContentService;
import com.appiancorp.suiteapi.process.exceptions.SmartServiceException;
import com.appiancorp.suiteapi.process.framework.AppianSmartService;
import com.appiancorp.suiteapi.process.framework.Input;
import com.appiancorp.suiteapi.process.framework.Required;
import com.appiancorp.suiteapi.process.framework.Unattended;
import com.appiancorp.suiteapi.process.palette.AutomationSmartServicesDocumentManagement;
import com.appiancorp.suiteapi.content.DocumentInputStream;
import org.apache.commons.io.IOUtils;
import java.util.Base64;

@AutomationSmartServicesDocumentManagement
@Unattended
public class DocumentToBase64SS extends AppianSmartService {

    private final ContentService cs;

    private long documentId;
    private String base64String;

    private static final Logger LOG = Logger.getLogger(DocumentToBase64SS.class); // Corrected Logger class name

    public DocumentToBase64SS(ContentService cs) {
        this.cs = cs;
    }

    @Input(required = Required.ALWAYS)
    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    @Override
    public void run() throws SmartServiceException {
        // Debugging: Log the documentId to ensure it's set correctly
        try {
            LOG.info("Document ID received: " + this.documentId);

            // Validate if documentId is valid before proceeding
            if (this.documentId <= 0) {
                throw createException(new Exception("Invalid documentId value"), "Invalid documentId", "Invalid documentId", null);
            }

            DocumentInputStream documentStream = cs.getDocumentInputStream(this.documentId);

            byte[] documentBytes = IOUtils.toByteArray(documentStream);

            // Convert the document content to Base64 encoding
            base64String = Base64.getEncoder().encodeToString(documentBytes);

        } catch (Exception e) {
            base64String = null;
            throw createException(e, "Error converting document to Base64", "Error converting document to Base64111",
                new Object[] { e.getMessage() });
        }

    }

    @Name("base64String")
    public String getBase64String() {
        return this.base64String;
    }

    private SmartServiceException createException(Throwable t, String userKey, String alertKey, Object args[]) {
        SmartServiceException.Builder b = new SmartServiceException.Builder(getClass(), t);
        b.userMessage(userKey, args);
        b.alertMessage(alertKey, args);
        b.addCauseToUserMessageArgs();
        b.addCauseToAlertMessageArgs();
        return b.build();
    }
}
