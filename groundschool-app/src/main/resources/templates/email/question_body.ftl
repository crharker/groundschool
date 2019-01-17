<html>
    <body>
        ${referenceMaterial}
        <h3>Question: ${questionText}</H3>
        <p>Answers:</p>
        <ol>
            <li><a href="http://localhost:8080/questions/${questionId}/answer/${userId}/${answerChoice1}">${answerChoice1}: ${answerText1}</a></li>
            <li><a href="http://localhost:8080/questions/${questionId}/answer/${userId}/${answerChoice2}">${answerChoice2}: ${answerText2}</a></li>
            <li><a href="http://localhost:8080/questions/${questionId}/answer/${userId}/${answerChoice3}">${answerChoice3}: ${answerText3}</a></li>
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
