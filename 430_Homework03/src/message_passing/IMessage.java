package message_passing;

/**
 * Minimal interface for messages between Component objects.
 */
public interface IMessage
{
    /**
     * Returns the sender of this message.
     * 
     * @return
     */
    Component getSender();

    /**
     * Returns the id for this message, assumed to be globally unique.
     * 
     * @return
     */
    int getId();

    /**
     * Returns a correlation id for this message, normally matching
     * the message to which it is a reply.
     * 
     * @return
     */
    int getCorrelationId();

    /**
     * Dispatch this method by calling the receiver's <code>handle</code>
     * method.
     * 
     * @param receiver
     */
    public void dispatch(Component receiver);
}