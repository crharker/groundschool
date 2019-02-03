<html>
    <body>
Unit: ${questionUnit}
Sub-Unit: ${questionSubUnit}
Learning Statement Code: ${questionLearningStatementCode}

${referenceMaterial}

Question: ${questionText}
Answers:
        <ol>
            <li><a href="http://localhost:8080/questions/${questionId}/answer/${userId}/${answerChoice1}">${answerChoice1}</a>: ${answerText1}</li>
            <li><a href="http://localhost:8080/questions/${questionId}/answer/${userId}/${answerChoice2}">${answerChoice2}</a>: ${answerText2}</li>
            <li><a href="http://localhost:8080/questions/${questionId}/answer/${userId}/${answerChoice3}">${answerChoice3}</a>: ${answerText3}</li>
            <li><a href="http://localhost:8080/questions/${questionId}/skip/${userId}/${answerChoice3}">${answerChoice3}</a>: ${answerText3}</li>
        </ol>
        <table>
            <tr>
                <th>Unit</th>
                <td>${questionUnit}</td>
            </tr>
            <tr>
                <th>Sub-Unit</th>
                <td>${questionSubUnit}</td>
            </tr>
            <tr>
                <th>Learning Statement Code</th>
                <td>${questionLearningStatementCode}</td>
            </tr>
        </table>
        <p>Unit: </p>
    </body>
</html>
