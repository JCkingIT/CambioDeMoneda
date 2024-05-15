package records;

import com.google.gson.annotations.SerializedName;

public record ErrorApi(String result, @SerializedName("error-type") String error_type) {
}
