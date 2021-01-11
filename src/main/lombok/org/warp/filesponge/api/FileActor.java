/*
 *     FileSponge
 *     Copyright (C) 2020 Andrea Cavalli
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package org.warp.filesponge.api;

import java.time.Duration;
import org.warp.filesponge.value.FileURI;
import reactor.core.publisher.Mono;

/**
 * FileActor sends signals to a mirror
 */
public interface FileActor<FURI extends FileURI> {

	/**
	 * Send a "delete file" signal
	 *
	 * @param fileURI File URI
	 * @return true if the signal can be sent. Cannot be empty.
	 */
	Mono<Boolean> deleteFile(FURI fileURI);

	/**
	 * Send a "download file" signal
	 *
	 * @param fileURI File URI
	 * @return true if the signal can be sent. Cannot be empty.
	 */
	Mono<Boolean> downloadFile(FURI fileURI);

	/**
	 * Check if this actor can handle signals for this file
	 *
	 * @param fileURI File URI
	 * @return true if the actor can send signals related to this file. Cannot be empty.
	 */
	Mono<Boolean> canHandleFile(FURI fileURI);

	/**
	 * Send a "download file" signal
	 *
	 * @param timeout if it's 0 the method will return immediately, if it's set the method will wait until a file
	 *                <b>download request</b> has been found, or the timeout time elapsed
	 * @return empty if no pending <b>download requests</b> has been found, true if the signal can be sent, false
	 * otherwise
	 */
	Mono<Boolean> downloadNextFile(Duration timeout);

	/**
	 * Send a "delete file" signal
	 *
	 * @param timeout if it's 0 the method will return immediately, if it's set the method will wait until a file
	 *                <b>delete request</b> has been found, or the timeout time elapsed
	 * @return empty if no pending <b>delete requests</b> has been found, true if the signal can be sent, false otherwise
	 */
	Mono<Boolean> deleteNextFile(Duration timeout);
}
