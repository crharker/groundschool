{
    "text": "${questionText}",
    "attachments": [
        {
            "text": "${referenceMaterial}\n${answer1Choice}) ${answer1Text}\n${answer2Choice}) ${answer2Text}\n${answer3Choice}) ${answer3Text}",
            "callback_id": "${callbackId}",
            "attachment_type": "default",
            "actions": [
                {
                    "name": "choice",
                    "text": "${answer1Choice}",
                    "type": "button",
                    "value": "${answer1Choice}"
                },
                {
                    "name": "choice",
                    "text": "${answer2Choice}",
                    "type": "button",
                    "value": "${answer2Choice}"
                },
                {
                    "name": "choice",
                    "text": "${answer3Choice}",
                    "type": "button",
                    "value": "${answer3Choice}"
                }
            ]
        }
    ]
}