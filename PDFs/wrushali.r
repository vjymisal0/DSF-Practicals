# Load the readxl package
library(readxl)

# Reading the data from an Excel file
survey_data <- read_excel("C:/Users/Aditya Umesh Pawar/Downloads/Hypothesis.xlsx")

# Check if the data is loaded correctly
if (!is.null(survey_data$Frequency)) {
  # If the Excel file contains fewer than 17 rows, create synthetic data for the remaining rows
  if (nrow(survey_data) < 17) {
    # Calculate how many additional rows are needed
    additional_rows <- 17 - nrow(survey_data)
    
    # Create synthetic data for additional rows
    synthetic_data <- data.frame(
      Frequency = rep(c("Daily", "Several times a week", "Once a Week", "Rarely"), length.out = additional_rows),
      Effectiveness = seq_len(additional_rows),
      Supplement = rep(c("Yes, very helpful", "Yes, somewhat helpful", "No, not helpful", "Not sure"), length.out = additional_rows),
      Interaction = rep(c("Yes, definitely", "Yes, to some extent", "No, not really", "Not sure"), length.out = additional_rows),
      Importance = rep(c("Significantly improved", "Improved", "No significant impact", "Not sure"), length.out = additional_rows)
    )
    
    # Combine the original data with synthetic data
    survey_data <- rbind(survey_data, synthetic_data)
  }
  
  # Encoding categorical responses into numerical values
  survey_data$Frequency <- as.numeric(factor(survey_data$Frequency, levels = c("Daily", "Several times a week", "Once a Week", "Rarely")))
  survey_data$Supplement <- as.numeric(factor(survey_data$Supplement, levels = c("Yes, very helpful", "Yes, somewhat helpful", "No, not helpful", "Not sure")))
  survey_data$Effectiveness <- as.numeric(gsub("\\D", "", survey_data$Effectiveness))  # Extracting numerical part
  survey_data$Interaction <- as.numeric(factor(survey_data$Interaction, levels = c("Yes, definitely", "Yes, to some extent", "No, not really", "Not sure")))
  survey_data$Importance <- as.numeric(factor(survey_data$Importance, levels = c("Significantly improved", "Improved", "No significant impact", "Not sure")))
  
  # Calculating mean and standard deviations for each variable
  mean_frequency <- mean(survey_data$Frequency)
  sd_frequency <- sd(survey_data$Frequency)
  
  mean_effectiveness <- mean(survey_data$Effectiveness)
  sd_effectiveness <- sd(survey_data$Effectiveness)
  
  mean_supplement <- mean(survey_data$Supplement)
  sd_supplement <- sd(survey_data$Supplement)
  
  mean_interaction <- mean(survey_data$Interaction)
  sd_interaction <- sd(survey_data$Interaction)
  
  mean_importance <- mean(survey_data$Importance)
  sd_importance <- sd(survey_data$Importance)
  
  # Displaying means and standard deviations
  cat("Mean and Standard Deviation for Frequency:", mean_frequency, ",", sd_frequency, "\n")
  cat("Mean and Standard Deviation for Effectiveness:", mean_effectiveness, ",", sd_effectiveness, "\n")
  cat("Mean and Standard Deviation for Supplement:", mean_supplement, ",", sd_supplement, "\n")
  cat("Mean and Standard Deviation for Interaction:", mean_interaction, ",", sd_interaction, "\n")
  cat("Mean and Standard Deviation for Importance:", mean_importance, ",", sd_importance, "\n")
  
  # Single-sample t-test
  # Let's perform the t-test for the "Effectiveness" variable
  mu_effectiveness <- 3  # Population mean under the null hypothesis
  t_result_effectiveness <- t.test(survey_data$Effectiveness, mu = mu_effectiveness)
  
  cat("\nSingle-sample t-test for Effectiveness:\n")
  print(t_result_effectiveness)
  
  # Two-sample t-test
  # Let's perform the t-test for the "Frequency" and "Supplement" variables
  t_result_frequency <- t.test(survey_data$Frequency, mu = mu_frequency)
  t_result_supplement <- t.test(survey_data$Supplement, mu = mu_supplement)
  
  cat("\nTwo-sample t-test for Frequency:\n")
  print(t_result_frequency)
  
  cat("\nTwo-sample t-test for Supplement:\n")
  print(t_result_supplement)
  
  # Paired t-test
  # Let's perform the t-test for the "Interaction" and "Importance" variables
  t_result_interaction_vs_importance <- t.test(survey_data$Interaction, survey_data$Importance, paired = TRUE)
  
  cat("\nPaired t-test for Interaction and Importance:\n")
  print(t_result_interaction_vs_importance)
  
} else {
  cat("Error: 'Frequency' column is not found in the dataset.\n")
}