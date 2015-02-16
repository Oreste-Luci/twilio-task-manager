# Twilio Task Manager

A simple task manager based on SMS with Twilio.

The root of the application contains a page with navigation buttons.

## Assigning a Task

The /sendTask.jsp page contains the form for creating a new task.

This application creates tasks and sends a SMS message to whoever has been assigned to it.

## Replying to the task

The assignee can reply via SMS with the response. The response can be either:

1. Yes
2. OK
3. SI
4. OK
5. NO

It also considers the following as a valid reply:

1. KO
2. Yeah
3. Nop
4. Never
5. N0

If the reply is not valid, then the system will send another SMS asking for a Yes/No reply. The reply is not case sensitive.

If more than one task has been assigned, then the answer is processed for the oldest unanswered (New/Undetermined) task assigend. This means that replies are assigned in the same order that the tasks where assigned.

## Viewing Replies

The /taskList page shows the list of all tasks in the system. Is shows the status and Dates/Times when they where created and when the reply was received.


