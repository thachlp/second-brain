### Run the script
```javascript
// Create a Trusted Types policy
const trustedTypesPolicy = window.trustedTypes.createPolicy('myTrustedTypesPolicy', {
    createScriptURL: (input) => input
});

// Creating a TrustedScriptURL using the policy
let jspdfSrc = trustedTypesPolicy.createScriptURL('https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.5.3/jspdf.debug.js');

let jspdf = document.createElement("script");

jspdf.onload = function () {
    let pdf = new jsPDF();
    let elements = document.getElementsByTagName("img");
    for (let i in elements) {
        let img = elements[i];
        if (!/^blob:/.test(img.src)) {
            continue;
        }
        let canvasElement = document.createElement('canvas');
        let con = canvasElement.getContext("2d");
        canvasElement.width = img.width;
        canvasElement.height = img.height;
        con.drawImage(img, 0, 0, img.width, img.height);
        let imgData = canvasElement.toDataURL("image/jpeg", 1.0);
        pdf.addImage(imgData, 'JPEG', 0, 0);
        pdf.addPage();
    }
    pdf.save("download.pdf");
};

jspdf.src = jspdfSrc; // Assigning the TrustedScriptURL to src
document.body.appendChild(jspdf);
```
