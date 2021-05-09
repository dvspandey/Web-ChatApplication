/**
 * @author Devesh Pandey
 * mailID:: dvspandey10@gmail.com
 */


package com.nt.bo;

import lombok.Data;

@Data //gives @Getter|@Setter|@toString|@EqualsAndHashCode|@RequiredArgsConstructor
public class RegisterBO {
	private String email;
	private String password;
}
