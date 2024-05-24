// Joshua Sanchez, Christopher Deluigi, Alejandro Ortega 
// Unit7_HW5
//11-10-2023





import java.io.*;
import java.util.*;

class Course {
    private String crn;
    private String prefix;
    private String title;
    private String graduateOrUndergraduate;
    private String modality;
    private String location;
    private String lab;
 

    // Class with lab or no lab 
    public Course(String crn, String prefix, String title, String graduateOrUndergraduate, String modality, String location, String lab) {
        this.crn = crn;
        this.prefix = prefix;
        this.title = title;
        this.graduateOrUndergraduate = graduateOrUndergraduate;
        this.modality = modality;
        this.location = location;
        this.lab = lab;
    }

    //online class
    public Course(String crn, String prefix, String title, String graduateOrUndergraduate, String modality) {
        this.crn = crn;
        this.prefix = prefix;
        this.title = title;
        this.graduateOrUndergraduate = graduateOrUndergraduate;
        this.modality = modality;
    }
    
    
    // lab lecture 
    public Course(String crn, String location) {
    	 this.crn = crn;
         this.location = location;
    }
    
    //Setters and getters 
	public String getCrn() {
		return crn;
	}
	public void setCrn(String crn) {
		this.crn = crn;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getGraduateOrUndergraduate() {
		return graduateOrUndergraduate;
	}
	public void setGraduateOrUndergraduate(String graduateOrUndergraduate) {
		this.graduateOrUndergraduate = graduateOrUndergraduate;
	}
	public String getModality() {
		return modality;
	}
	public void setModality(String modality) {
		this.modality = modality;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getLab() {
		return lab;
	}
	public void setLab(String lab) {
		this.lab = lab;
	}

	//To get the number of Parameters 
	public int getParameterCount() {
	        int count = 0;
	        if (crn != null) count++;
	        if (prefix != null) count++;
	        if (title != null) count++;
	        if (graduateOrUndergraduate != null) count++;
	        if (modality != null) count++;
	        if (location != null) count++;
	        if (lab != null) count++;
	        return count;
	}
 
    
    
}

public class filehw {
    public static void main(String[] args) {
        List<Course> courses = new ArrayList<>();
        int onlineCount = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader("lec.txt"));
            String line; // will represent the line in the textfile 
           
           
          
            while ((line = reader.readLine()) != null) {
            	

                String[] words = line.split(",");
                
                String lastWord = words[words.length -1];
                //System.out.println("Last word: " + lastWord);
                
                if(lastWord.equalsIgnoreCase("Online")) {
                	// 0 - 4 total 5
                	
                	Course course = new Course(words[0],words[1],words[2],words[3],words[4]);
                	courses.add(course);
                	onlineCount++;
                }
                else if(lastWord.equalsIgnoreCase("No") || lastWord.equalsIgnoreCase("yes")) {
                	// 0-6 total 7
                	Course course = new Course(words[0],words[1],words[2],words[3],words[4],words[5],words[6]);
                	courses.add(course);
                }
                else if(words.length < 3) {
                	Course course = new Course(words[0],words[1]);
                	courses.add(course);
                	
                }
                
                
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
            
        }
        
        
        //Finding classes 
        String choice;
        Scanner myScan = new Scanner (System.in);
        System.out.println("There are " + onlineCount + " online lectures offered");
        
        System.out.println("Enter the classroom: ");
        choice = myScan.nextLine();
        System.out.println("\n\nThe crns held in " + choice + " are listed below:");
        
   
        for(Course course: courses){
        	
        	//System.out.println(course.getCrn());
   
        	//Will get the crn from the main class
        	if(course.getParameterCount () >  3 &&  (course.getModality().compareToIgnoreCase("F2F") == 0 || course.getModality().compareToIgnoreCase("mixed") == 0 )) {
        		
        			
        			// this will get the crn from the core class (NO LAB)
        			if(course.getLocation().compareTo(choice) == 0) {
        				System.out.println(course.getCrn());
        			}
        	}
        	
        	if(course.getParameterCount() < 3) {
        		if(course.getLocation().compareTo(choice) == 0) {
    				System.out.println(course.getCrn());
    			}
        		
        	}
        	
        }
       
        
        //This will make the textfile  "lecturesOnly.txt"
        
        int count = 0;
        
        
        try {
        	FileWriter fileWriter = new FileWriter("lecturesOnly.txt");
        	BufferedWriter writer = new BufferedWriter(fileWriter);
        	
        	
        	
        	writer.write("Online Lectures:");
    		writer.newLine();
    		writer.newLine();
    		
        	
        	for(Course classes: courses){
        		
        		

        		//Will print the online classes first 
        		
        		
        		if( classes.getParameterCount() > 2 &&(classes.getModality().compareToIgnoreCase("online") == 0)) {
        			
        		
        			writer.write(classes.getCrn()+ "," + classes.getPrefix() + "," + classes.getTitle() + "," + classes.getGraduateOrUndergraduate() + "," + classes.getModality() );
        			writer.newLine();
        		}
        		
        		
        	
        	}
        	
        	writer.newLine();
        	writer.newLine();
        	
        	writer.write("Lectures with no lab\n\n");
        	
        	//Will print the no lab courses.
        	for(Course course: courses) {
        		
        		
        		
        		if(course.getParameterCount() > 2 && (course.getModality().compareToIgnoreCase("f2f")== 0 || course.getModality().compareToIgnoreCase("mixed")== 0) && course.getLab().compareToIgnoreCase("no") == 0) {
        			
        			writer.write(course.getCrn()+"," + course.getPrefix() + "," + course.getTitle() + "," + course.getGraduateOrUndergraduate() + "," + course.getModality()  + "," + course.getLocation()  + "," + course.getLab() );
        			writer.newLine();
        		}
        		
        		
        		
        		
        	}
        	
        	
        	
        	
        	
        	
        	
        	
        	writer.close();
        }catch(IOException e) {
        	e.printStackTrace();
     	   //System.out.println("There was an error ");
        }
     
        
       System.out.println("Goodbye!");
    }
}
























































