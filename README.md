
# Appian Extra Tools

A bunch of useful Appian custom functions
| name |type|
|--|--|
| beautifyjsontext  | function |
  | documentToBase64| function |
  | documentToBase64SS| Smart Service|
  
  

## beautifyjsontext Function

beautifies a JSON text using jackson java lib

  

### Syntax

`beautifyjsontext(uglyJson)`

  

### Inputs 
- uglyJson: (text) the json to be beautified

  

### Returns
- Text: beautified JSON



  


## documentToBase64 Function

encodes a document to base64 format
  

### Syntax

`documentToBase64(document)`

  

### Inputs 
- document: (document) the document to be encoded
  

### Returns
- Text: base64 encoding for the document



## documentToBase64 Smart Service

encodes a document to base64 format
  
  

### Inputs 
- documentId: (Integer) the ID for the document to be encoded	
  

### Outputs
- base64String: (text) base64 encoding for the document



  

