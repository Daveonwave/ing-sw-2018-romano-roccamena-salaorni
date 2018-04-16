package service;

import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;

public class DocumentsExample {
    //TODO: updating
    //Servizio documenti
    //Write, append o read su documenti

    private class Document implements Serializable {
        //Documento

        private String name;
        private String content;

        private Document(String name, String content) {
            this.name = name;
            this.content = content;
        }

        public String getName() {
            return name;
        }
        public String getContent() {
            return content;
        }

        public void setName(String name) {
            this.name = name;
        }
        public void setContent(String content) {
            this.content = content;
        }
    }

    private abstract class DocumentContainer implements Serializable {
        //Wrapper documento

        private Document document;

        public DocumentContainer(Document document) {
            this.document = document;
        }

        public Document getDocument() {
            return document;
        }

        public void setDocument(Document document) {
            this.document = document;
        }
    }

    private enum DocumentEvent {
        //Evento del servizio documenti

        WRITE, APPEND, READ;
    }

    private class ControlInput extends DocumentContainer implements Serializable {
        //Input del servizio documenti

        private String user;

        public ControlInput(Document document, String user) {
            super(document);
            this.user = user;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
    }

    private class ControlOutput extends DocumentContainer implements Serializable {
        //Output del servizio documenti

        private String message;

        public ControlOutput(Document document, String message) {
            super(document);
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
    private class DocumentsDatabase extends ArrayList<Document> {
        //Stato del servizio documenti
        //Insieme di tutti i documenti disponibili
    }

    private interface Printer {
        //Output personalizzato su console

        void print(String text);
    }

    private class DocumentsController extends ServiceController<DocumentEvent, ControlInput, ControlOutput, DocumentsDatabase> implements Printer {
        //Controllore centrale documenti

        public DocumentsController(DocumentsDatabase documents) {
            super(documents);
        }

        public void print(String text) {
            System.out.println("[CONTROLLER] : " + text);
        }

        private ControlOutput write(ControlInput input) {
            print(input.getUser() + " requested write on " + input.getDocument().name);

            for (Document d: getServiceState()) {
                if (d.name.equals(input.getDocument().name)) {
                    print("writing " + input.getDocument().name);

                    d.setContent(input.getDocument().getContent());
                    return new ControlOutput(input.getDocument(), input.getDocument().getName() + " writed");
                }
            }

            getServiceState().add(new Document(input.getDocument().getName(), input.getDocument().getContent()));
            return new ControlOutput(input.getDocument(), input.getDocument().getName() + " created");
        }
        private ControlOutput append(ControlInput input) {
            print(input.getUser() + " requested append on " + input.getDocument().name);

            for (Document d: getServiceState()) {
                if (d.name.equals(input.getDocument().name)) {
                    print("appending " + input.getDocument().name);

                    d.setContent(d.getContent() + input.getDocument().getContent());
                    return new ControlOutput(input.getDocument(), input.getDocument().getName() + " modfified");
                }
            }

            getServiceState().add(new Document(input.getDocument().getName(), input.getDocument().getContent()));
            return new ControlOutput(input.getDocument(), input.getDocument().getName() + " created");
        }
        private ControlOutput read(ControlInput input) {
            print(input.getUser() + " requested read on " + input.getDocument().name);

            for (Document d: getServiceState()) {
                if (d.name.equals(input.getDocument().name)) {
                    print("reading " + input.getDocument().name);

                    return new ControlOutput(input.getDocument(), input.getDocument().getName() + " read");
                }
            }

            return new ControlOutput(null, input.getDocument().getName() + " not found");
        }

        public ControlOutput handleEvent(DocumentEvent event, ControlInput input) throws ServiceException {
            switch (event) {
                case WRITE:
                    return write(input);
                case APPEND:
                    return append(input);
                case READ:
                    return read(input);
                default:
                    throw new ServiceException("invalid request");
            }
        }
    }
    private abstract class DocumentsUser extends ServiceUser<DocumentEvent, ControlInput, ControlOutput> implements Printer, Runnable{
        //Utilizzatore astratto documenti
        //Implementato come thread (richieste asincrone ad un server)

        private String name;

        public final String sm = "max secrets";
        public final String sd = "dave secrets";
        public final String sl = "lore secrets";
        public final String n1 = "file";
        public final String n2 = "password";
        public final String n3 = "book";
        public final String n4 = "facebook";
        public final String n5 = "hotel";

        public DocumentsUser(ServiceUpdater<DocumentEvent, ControlInput, ControlOutput> service_updater, String name) {
            super(service_updater);
            this.name = name;
        }

        public void print(String text) {
            System.out.println("[" + name.toUpperCase() + "] : " + text);
        }

        public void write(Document document) {
            ControlOutput response = null;

            try {
                response = notifyEvent(DocumentEvent.WRITE, new ControlInput(document, name));
            } catch (ServiceException e) {
                print(e.getMessage());
            }

            print(response.getMessage());
        }

        public void append(Document document) {
            ControlOutput response = null;

            try {
                response = notifyEvent(DocumentEvent.APPEND, new ControlInput(document, name));
            } catch (ServiceException e) {
                print(e.getMessage());
            }

            print(response.getMessage());
        }

        public void read(String file) {
            ControlOutput response = null;

            try {
                response = notifyEvent(DocumentEvent.READ, new ControlInput(new Document(file, ""), name));
            } catch (ServiceException e) {
                print(e.getMessage());
            }

            if (response.getDocument() == null) {
                print(file + " not found");
            } else {
                print(response.getMessage());
                print(response.getDocument().getContent());
            }
        }
    }

    //Utilizzatori concreti
    private class Max extends DocumentsUser {
        public Max(ServiceUpdater<DocumentEvent, ControlInput, ControlOutput> service_updater) {
            super(service_updater, "max");
        }

        //Richieste effettuate
        public void run() {
            write(new Document(sm, "max is great"));
            append(new Document(n2, "yeeee"));
            append(new Document(n1, "fantastic"));
            append(new Document(n5, "oleee"));
            append(new Document(n4, "why not"));
            read(sd);
            read(sl);
        }
    }
    private class Dave extends DocumentsUser {
        public Dave(ServiceUpdater<DocumentEvent, ControlInput, ControlOutput> service_updater) {
            super(service_updater, "dave");
        }

        //Richieste effettuate
        public void run() {
            append(new Document(n3, "my name"));
            write(new Document(sd, "dave is bad"));
            append(new Document(n1, "fantastic"));
            read(sm);
            write(new Document(n3, "barman"));
            append(new Document(n1, "because"));
            read(sl);
        }
    }
    private class Lore extends DocumentsUser {
        public Lore(ServiceUpdater<DocumentEvent, ControlInput, ControlOutput> service_updater) {
            super(service_updater, "lore");
        }

        private void maxPrint(String text) {
            System.out.println("LORE : " + text);
        }

        //Richieste effettuate
        public void run() {
            write(new Document(sl, "lore is bad"));
            read(n1);
            write(new Document(n2, "lore was here"));
            append(new Document(n3, "like me"));
            read(n2);
            append(new Document(n5, "harry potter"));
        }
    }

    @Test
    public void example() {
        //Servizio documenti
        DocumentsController controller = new DocumentsController(new DocumentsDatabase());

        //Utilizzatori servizio
        Thread max = new Thread(new Max(controller));
        Thread dave = new Thread(new Dave(controller));
        Thread lore = new Thread(new Lore(controller));

        //Inizio richieste utilizzatori
        max.start();
        dave.start();
        lore.start();

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
