/*
 * ===========================================================================
 * (c) Copyright IBM Corp. 2022, 2022 All Rights Reserved
 * ===========================================================================
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.
 *
 * IBM designates this particular file as subject to the "Classpath" exception
 * as provided by IBM in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, see <http://www.gnu.org/licenses/>.
 * ===========================================================================
 */
import java.time.Instant;
import java.time.format.DateTimeParseException;

public class DateUtil {

	public static void main(String... args) {
		if (args.length > 0) {
			switch (args[0]) {
			case "epoch":
				if (args.length == 1) {
					System.out.println(Instant.now().getEpochSecond());
					return;
				} else if (args.length == 2) {
					try {
						System.out.println(Instant.parse(args[1]).getEpochSecond());
						return;
					} catch (DateTimeParseException e) {
						System.err.format("Invalid iso-time: '%s'%n", args[1]);
						System.exit(1);
					}
				}
				break;

			case "iso8601":
				if (args.length == 2) {
					try {
						System.out.println(Instant.ofEpochSecond(Long.parseLong(args[1])));
						return;
					} catch (NumberFormatException e) {
						System.err.format("Invalid seconds: '%s'%n", args[1]);
						System.exit(1);
					}
				}
				break;

			case "test":
				if (args.length == 1) {
					main("epoch");
					main("epoch", "2022-09-28T15:44:07Z"); // -> 1664379847
					main("iso8601", "1664379847"); // -> 2022-09-28T15:44:07Z
					return;
				}
				break;

			default:
				break;
			}
		}

		System.err.println("Usage: DateUtil epoch [iso-time] | iso8601 <seconds>");
		System.exit(1);
	}

}
