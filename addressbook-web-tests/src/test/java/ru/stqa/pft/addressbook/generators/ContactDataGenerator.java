package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;


public class ContactDataGenerator {
    @Parameter(names = "-c", description = "Contact count")
    public int count;
    @Parameter(names = "-f", description = "Target file")
    public String file;


    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
            generator.run();
        } catch (ParameterException ex) {
            jCommander.usage();
        }
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        saveAsJson(contacts, new File(file));
    }


    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        for (int i = 0; i < count; i++) {
            contacts.add(new ContactData()
                    .withFirstName(String.format("firstname %s", i)).withLastName(String.format("lastname %s", i))
                    .withHomePhone(String.format("+7%s", i)).withMobilePhone(String.format("+8%s", i)).withWorkPhone(String.format("+9%s", i))
                    .withEmail_1(String.format("%s@mail.ru", i)).withEmail_2(String.format("%s@mail.com", i)).withEmail_3(String.format("%s@mail.net", i))
                    .withAddress(String.format("address_%s", i))
                    .withGroup("name 0"));
        }
        return contacts;
    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)) {
            writer.write(json);
        }
    }

}
