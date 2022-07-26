package exercises.interview.model.apis;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class DataUsaResponse {

    @SerializedName("data")
    ArrayList<Datum> data;

    @SerializedName("source")
    ArrayList<Source> source;

    @SerializedName("error")
    public String error;

    public class Annotations {
        @SerializedName("source_name")
        String source_name;

        @SerializedName("source_description")
        String source_description;

        @SerializedName("dataset_name")
        String dataset_name;

        @SerializedName("dataset_link")
        String dataset_link;

        @SerializedName("table_id")
        String table_id;

        @SerializedName("topic")
        String topic;

        @SerializedName("subtopic")
        String subtopic;

        public String getSource_name() {
            return source_name;
        }

        public void setSource_name(String source_name) {
            this.source_name = source_name;
        }

        public String getSource_description() {
            return source_description;
        }

        public void setSource_description(String source_description) {
            this.source_description = source_description;
        }

        public String getDataset_name() {
            return dataset_name;
        }

        public void setDataset_name(String dataset_name) {
            this.dataset_name = dataset_name;
        }

        public String getDataset_link() {
            return dataset_link;
        }

        public void setDataset_link(String dataset_link) {
            this.dataset_link = dataset_link;
        }

        public String getTable_id() {
            return table_id;
        }

        public void setTable_id(String table_id) {
            this.table_id = table_id;
        }

        public String getTopic() {
            return topic;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public String getSubtopic() {
            return subtopic;
        }

        public void setSubtopic(String subtopic) {
            this.subtopic = subtopic;
        }

        public String toString() {
            return (new GsonBuilder()).setPrettyPrinting().create().toJson(this);
        }

    }

    public class Datum {
        @SerializedName("ID State")
        String iDState;

        @SerializedName("State")
        String state;

        @SerializedName("ID Year")
        int iDYear;

        @SerializedName("Year")
        String year;

        @SerializedName("Population")
        int population;

        @SerializedName("Slug State")
        String slugState;

        public String getiDState() {
            return iDState;
        }

        public void setiDState(String iDState) {
            this.iDState = iDState;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getiDYear() {
            return iDYear;
        }

        public void setiDYear(int iDYear) {
            this.iDYear = iDYear;
        }

        public String getYear() {
            return year;
        }

        public void setYear(String year) {
            this.year = year;
        }

        public int getPopulation() {
            return population;
        }

        public void setPopulation(int population) {
            this.population = population;
        }

        public String getSlugState() {
            return slugState;
        }

        public void setSlugState(String slugState) {
            this.slugState = slugState;
        }

        public String toString() {
            return (new GsonBuilder()).setPrettyPrinting().create().toJson(this);
        }

    }

    public class Source {
        @SerializedName("measures")
        ArrayList<String> measures;

        @SerializedName("annotations")
        Annotations annotations;

        @SerializedName("name")
        String name;

        @SerializedName("substitutions")
        ArrayList<Object> substitutions;

        public ArrayList<String> getMeasures() {
            return measures;
        }

        public void setMeasures(ArrayList<String> measures) {
            this.measures = measures;
        }

        public Annotations getAnnotations() {
            return annotations;
        }

        public void setAnnotations(Annotations annotations) {
            this.annotations = annotations;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public ArrayList<Object> getSubstitutions() {
            return substitutions;
        }

        public void setSubstitutions(ArrayList<Object> substitutions) {
            this.substitutions = substitutions;
        }

        public String toString() {
            return (new GsonBuilder()).setPrettyPrinting().create().toJson(this);
        }
    }

    public ArrayList<Datum> getData() {
        return data;
    }

    public void setData(ArrayList<Datum> data) {
        this.data = data;
    }

    public ArrayList<Source> getSource() {
        return source;
    }

    public void setSource(ArrayList<Source> source) {
        this.source = source;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String toString() {
        return (new GsonBuilder()).setPrettyPrinting().create().toJson(this);
    }


}
