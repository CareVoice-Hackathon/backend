from deepgram import DeepgramClient, PrerecordedOptions
from dotenv import load_dotenv
import os

# Load environment variables from .env file
load_dotenv()

# Initialize the Deepgram client
# Set DEEPGRAM_API_KEY as an environment variable
DEEPGRAM_API_KEY = os.getenv('DEEPGRAM_API_KEY')
deepgram = DeepgramClient(DEEPGRAM_API_KEY)

def transcribe_audio():
    # Open the audio file and read its content
    with open("demo_htt_speech_voice.mp3", "rb") as audio:
        audio_content = audio.read()
        
        # Create transcription options
        options = PrerecordedOptions(
            model="nova-3",  # Using their latest model
            smart_format=True,
            language="en-US"
        )
        
        # Send the audio content to Deepgram using the rest API
        response = deepgram.listen.rest.v("1").transcribe_file({"buffer": audio_content}, options)
        
        # Get the transcript
        transcript = response.results.channels[0].alternatives[0].transcript
        
        # Save the transcript to a text file
        with open("htt_voice_to_text_output.txt", "w") as text_file:
            text_file.write(transcript)
            
        print("Transcription completed and saved to htt_voice_to_text_output.txt")

# Run the function
if __name__ == "__main__":
    transcribe_audio() 