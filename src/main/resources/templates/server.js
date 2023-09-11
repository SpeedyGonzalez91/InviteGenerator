const express = require('express');
const bodyParser = require('body-parser');
require('path');
require('fs');
const app = express();
const port = 3000;

// Serve static files (HTML, CSS, JavaScript)
app.use(express.static('public'));

// Parse JSON and URL-encoded form data
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({ extended: true }));

// Define an endpoint for generating invitations
app.post('/generate-invitation', (req, res) => {
    try {
        // Process the request body (form data or JSON)
        const requestData = req.body;

        // Generate the invitation (you can use your OpenAI logic here)
        const generatedInvitation = generateInvitation(requestData);

        // Send the generated invitation as a response
        res.send(generatedInvitation);
    } catch (error) {
        console.error(error);
        res.status(500).send('Error generating invitation.');
    }
});

// Start the server
app.listen(port, () => {
    console.log(`Server is listening on port ${port}`);
});

// Your invitation generation logic
function generateInvitation(requestData) {
    // Implement your invitation generation logic here
    // You can use OpenAI or other methods to generate the invitation
    const invitation = {
        location: requestData.location,
        reason: requestData.reason,
        instruction: requestData.instruction,
        timezone: requestData.timezone,
        // Additional fields as needed
    };

    // Convert the invitation to JSON and return it
    return JSON.stringify(invitation);
}
